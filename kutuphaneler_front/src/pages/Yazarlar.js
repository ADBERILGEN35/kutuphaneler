import React, {Component} from 'react';
import styled from 'styled-components';
import Datatable from 'react-bs-datatable';
import {YazarlarService} from "../api/yazarlar";
import Modal from 'react-modal';

const GridWrapper = styled.div`
  display: grid;
  grid-gap: 10px;
  margin-top: 1em;
  margin-left: 6em;
  margin-right: 6em;
  grid-template-columns: repeat(12, 1fr);
  grid-auto-rows: minmax(25px, auto);
`;

export class Yazarlar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            yazarlar: [],
            modalIsOpen: false,
            currYazarBooks: [],
            name: "",
            surname: ""
        }
    }

    async componentDidMount() {
        const results = await YazarlarService.getAll()
        this.setState({yazarlar: results})
    }

    closeModal = () => {
        this.setState({modalIsOpen: false});
    }

    handleNameChange = (e) => {
        this.setState({name: e.target.value});
    }
    handleSurnameChange = (e) => {
        this.setState({surname: e.target.value});
    }

    onRowClick = async (row) => {
        const results = await YazarlarService.getAllBooks(row.yazarId);
        this.setState({currYazarBooks: results})
        this.setState({modalIsOpen: true})
    }

    saveYazar= async () => {
        const payload = {
            yazarAd: this.state.name,
            yazarSoyad: this.state.surname
        }
        await YazarlarService.saveYazar(payload)
        this.setState({
            name: "",
            surname: ""
        })
    }

    render() {
        const header = [
            {title: 'yazarAd', prop: 'yazarAd'},
            {title: 'yazarSoyad', prop: 'yazarSoyad'},
            {title: 'yazarId', prop: 'yazarId'},
        ];

        const bookHeaders = [
            {title: 'kitapAdi', prop: 'kitapAdi'},
            {title: 'yayinTarihi', prop: 'yayinTarihi'},
            {title: 'isbn', prop: 'isbn'},
        ]

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
                <h2>Yazarlar</h2>
                <Datatable tableHeaders={header} tableBody={this.state.yazarlar} onRowClick={this.onRowClick}/>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onRequestClose={this.closeModal}
                    style={customStyles}
                    contentLabel="Yazarın Kitapları"
                >
                    <Datatable tableHeaders={bookHeaders} tableBody={this.state.currYazarBooks}
                               onRowClick={this.onRowClick}/>
                </Modal>
                <form style={{marginLeft: 80, width: 300}}>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputEmail1">Yazar Adı</label>
                        <input className="form-control" id="email" aria-describedby="Yazar Adı" value={this.state.name} onChange={this.handleNameChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Yazar Soyadı</label>
                        <input className="form-control" id="password" placeholder="Yazar Soyadı" value={this.state.surname} onChange={this.handleSurnameChange}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.saveYazar}>
                        Ekle
                    </button>
                </form>
            </GridWrapper>
        )
    }
}