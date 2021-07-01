import React, {Component} from 'react';
import styled from 'styled-components';
import Datatable from 'react-bs-datatable';
import {Emanet} from "../api/emanets";
import Modal from "react-modal";

const GridWrapper = styled.div`
  display: grid;
  grid-gap: 10px;
  margin-top: 1em;
  margin-left: 6em;
  margin-right: 6em;
  grid-template-columns: repeat(12, 1fr);
  grid-auto-rows: minmax(25px, auto);
`;

export class Emanetler extends Component {
    constructor(props) {
        super(props);
        this.state = {
            emanets: [],
            uyeId: "",
            kitapId: "",
            kutuphaneId: "",
            emanetTarihi: "",
            teslimTarihi: "",
            modalIsOpen: false,
            currEmanet: {}
        }
    }

    async componentDidMount() {
        const results = await Emanet.getAll()
        this.setState({emanets: results})
    }

    handleUyeIdChange = (e) => {
        this.setState({uyeId: e.target.value});
    }

    handleKitapIdChange = (e) => {
        this.setState({kitapId: e.target.value});
    }

    handleKutuphaneIdChange = (e) => {
        this.setState({kutuphaneId: e.target.value});
    }

    handleEmanetTarihiChange = (e) => {
        this.setState({emanetTarihi: e.target.value});
    }

    handleTeslimTarihiChange = (e) => {
        this.setState({teslimTarihi: e.target.value});
    }

    handleUpdateEmanetTarihiChange = (e) => {
        const currEmanet = {
            ...this.state.currEmanet,
            emanetTarihi : e.target.value
        }
        this.setState({currEmanet: currEmanet});
    }

    handleUpdateTeslimTarihiChange = (e) => {
        const currEmanet = {
            ...this.state.currEmanet,
            teslimTarihi: e.target.value
        }
        this.setState({currEmanet: currEmanet});
    }

    saveEmanet = async () => {
        const payload = {
            uyeId: this.state.uyeId,
            kitapId: this.state.kitapId,
            kutuphaneId: this.state.kutuphaneId,
            emanetTarihi: this.state.emanetTarihi,
            teslimTarihi: this.state.teslimTarihi,
        }
        await Emanet.saveEmanet(payload)
        this.setState({
            uyeId: "",
            kitapId: "",
            kutuphaneId: "",
            emanetTarihi: "",
            teslimTarihi: "",
        })
    }

    updateEmanet = async () => {
        const payload = {
            emanetId: this.state.currEmanet.emanetId,
            emanetTarihi: this.state.currEmanet.emanetTarihi,
            teslimTarihi: this.state.currEmanet.teslimTarihi,
        }
        await Emanet.updateEmanet(payload)
        this.setState({
            currEmanet : {}
        })
    }

    silEmanet = async (id) => {
        await Emanet.deleteById(id)
        this.setState({
            currEmanet: {}
        })
    }

    onRowClick = async (row) => {
        const results = await Emanet.getById(row.emanetId);
        console.log("anan",results)
        this.setState({currEmanet: results})
        this.setState({modalIsOpen: true})
    }

    render() {
        const header = [
            {title: 'emanetTarihi', prop: 'emanetTarihi'},
            {title: 'teslimTarihi', prop: 'teslimTarihi'},
            {title: 'kitapAdi', prop: 'kitapAdi'},
            {title: 'kutuphaneAdi', prop: 'kutuphaneAdi'},
            {title: 'uyeAdi', prop: 'uyeAdi'},
            {title: 'uyeSoyadi', prop: 'uyeSoyadi'},
        ];

        const customStyles = {
            content: {
                top: '50%',
                left: '50%',
                right: 'auto',
                bottom: 'auto',
                marginRight: '-50%',
                transform: 'translate(-50%, -50%)',
            },
        };



        return (
            <GridWrapper>
                <h2>Emanetler</h2>
                <Datatable tableHeaders={header} tableBody={this.state.emanets} onRowClick={this.onRowClick}/>
                <form style={{marginLeft: 80, width: 300}}>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputEmail1">Uye Id</label>
                        <input className="form-control" placeholder="Uye Id" value={this.state.uyeId}
                               onChange={this.handleUyeIdChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Kitap Id</label>
                        <input className="form-control" placeholder="Kitap Id" value={this.state.kitapId}
                               onChange={this.handleKitapIdChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Kutuphane Id</label>
                        <input className="form-control" placeholder="Kutuphane Id" value={this.state.kutuphaneId}
                               onChange={this.handleKutuphaneIdChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Emanet Tarihi</label>
                        <input className="form-control" placeholder="Emanet Tarihi" value={this.state.emanetTarihi}
                               onChange={this.handleEmanetTarihiChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Teslim Tarihi</label>
                        <input className="form-control" placeholder="Teslim Tarihi" value={this.state.teslimTarihi}
                               onChange={this.handleTeslimTarihiChange}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.saveEmanet}>
                        Ekle
                    </button>
                </form>

                <Modal
                    isOpen={this.state.modalIsOpen}
                    onRequestClose={this.closeModal}
                    style={customStyles}
                    contentLabel="Yazarın Kitapları"
                >
                    <form style={{marginLeft: 80, width: 300}}>
                        <div className="form-group text-left">
                            <label htmlFor="exampleInputEmail1">Uye Id</label>
                            <input className="form-control" placeholder="Uye Id" disabled={true} value={this.state.currEmanet.uyeId}
                                   onChange={this.handleUyeIdChange}/>
                        </div>
                        <div className="form-group text-left">
                            <label htmlFor="exampleInputPassword1">Kitap Id</label>
                            <input className="form-control" placeholder="Kitap Id" disabled={true} value={this.state.currEmanet.kitapId}
                                   onChange={this.handleKitapIdChange}/>
                        </div>
                        <div className="form-group text-left">
                            <label htmlFor="exampleInputPassword1">Kutuphane Id</label>
                            <input className="form-control" placeholder="Kutuphane Id" disabled={true} value={this.state.currEmanet.kutuphaneId}
                                   onChange={this.handleKutuphaneIdChange}/>
                        </div>
                        <div className="form-group text-left">
                            <label htmlFor="exampleInputPassword1">Emanet Tarihi</label>
                            <input className="form-control" placeholder="Emanet Tarihi" value={this.state.currEmanet.emanetTarihi}
                                   onChange={this.handleUpdateEmanetTarihiChange}/>
                        </div>
                        <div className="form-group text-left">
                            <label htmlFor="exampleInputPassword1">Teslim Tarihi</label>
                            <input className="form-control" placeholder="Teslim Tarihi" value={this.state.currEmanet.teslimTarihi}
                                   onChange={this.handleUpdateTeslimTarihiChange}/>
                        </div>
                        <button type="submit" className="btn btn-primary" onClick={this.updateEmanet}>
                            Güncelle
                        </button>
                        <button type="submit" className="btn btn-danger" onClick={this.silEmanet(this.state.currEmanet.emanetId)}>
                            Sil
                        </button>
                    </form>
                </Modal>

            </GridWrapper>
        )
    }
}