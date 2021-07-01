import React, {Component} from 'react';
import styled from 'styled-components';
import Datatable from 'react-bs-datatable';
import {UyelerService} from "../api/uyeler";

const GridWrapper = styled.div`
  display: grid;
  grid-gap: 10px;
  margin-top: 1em;
  margin-left: 6em;
  margin-right: 6em;
  grid-template-columns: repeat(12, 1fr);
  grid-auto-rows: minmax(25px, auto);
`;

export class Uyeler extends Component {
    constructor(props) {
        super(props);
        this.state = {
            uyeler: [],
            name: "",
            surname: "",
            cinsiyet: "",
            telefon: "",
            eposta: "",
            adresId: 1
        }
    }

    async componentDidMount() {
        const results = await UyelerService.getAll()
        this.setState({uyeler: results})
    }

    handleNameChange = (e) => {
        this.setState({name: e.target.value});
    }
    handleSurnameChange = (e) => {
        this.setState({surname: e.target.value});
    }
    handleCinsiyetChange = (e) => {
        this.setState({cinsiyet: e.target.value});
    }
    handlePhoneChange = (e) => {
        this.setState({telefon: e.target.value});
    }
    handleEpostaChange = (e) => {
        this.setState({eposta: e.target.value});
    }
    handleAdresIdChange = (e) => {
        this.setState({adresId: e.target.value});
    }
    saveUye = async () => {
        const payload = {
            uyeAd: this.state.name,
            uyeSoyad: this.state.surname,
            cinsiyet: this.state.cinsiyet,
            telefon: this.state.telefon,
            eposta: this.state.eposta,
            adresId: this.state.adresId
        }
        await UyelerService.saveUye(payload)
        this.setState({
            name: "",
            surname: "",
            cinsiyet: "",
            telefon: "",
            eposta: "",
            adresId: 1
        })
    }

    render() {
        const header = [
            {title: 'uyeId', prop: 'uyeId'},
            {title: 'uyeAd', prop: 'uyeAd'},
            {title: 'uyeSoyad', prop: 'uyeSoyad'},
            {title: 'cinsiyet', prop: 'cinsiyet'},
            {title: 'telefon', prop: 'telefon'},
            {title: 'eposta', prop: 'eposta'},
            {title: 'sokak', prop: 'sokak'},
            {title: 'cadde', prop: 'cadde'},

        ];
        return (
            <GridWrapper>
                <h2>Uyeler</h2>
                <Datatable tableHeaders={header} tableBody={this.state.uyeler}/>;
                <form style={{marginLeft: 80, width: 300}}>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputEmail1">Üye Adı</label>
                        <input className="form-control" id="email" placeholder="Üye Adı" value={this.state.name}
                               onChange={this.handleNameChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Üye Soyadı</label>
                        <input className="form-control" id="password" placeholder="Üye Soyadı"
                               value={this.state.surname} onChange={this.handleSurnameChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Cinsiyet</label>
                        <input className="form-control" id="password" placeholder="Cinsiyet" value={this.state.cinsiyet}
                               onChange={this.handleCinsiyetChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Telefon</label>
                        <input className="form-control" id="password" placeholder="Telefon" value={this.state.telefon}
                               onChange={this.handlePhoneChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Eposta</label>
                        <input className="form-control" id="password" placeholder="Eposta" value={this.state.eposta}
                               onChange={this.handleEpostaChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Adres Id</label>
                        <input className="form-control" id="password" placeholder="Adres Id" value={this.state.adresId}
                               onChange={this.handleAdresIdChange}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.saveUye}>
                        Ekle
                    </button>
                </form>
            </GridWrapper>
        )
    }
}