import React, {Component} from 'react';
import styled from 'styled-components';
import Datatable from 'react-bs-datatable';
import {Kutuphane} from "../api/kutuphaneler";

const GridWrapper = styled.div`
  display: grid;
  grid-gap: 10px;
  margin-top: 1em;
  margin-left: 6em;
  margin-right: 6em;
  grid-template-columns: repeat(12, 1fr);
  grid-auto-rows: minmax(25px, auto);
`;

export class Kutuphaneler extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kutups: [],
            name: "",
            id: ""
        }
    }

    async componentDidMount() {
        const results = await Kutuphane.getAll()
        this.setState({kutups: results})
    }

    handleNameChange = (e) => {
        this.setState({name: e.target.value});
    }
    handleIdChange = (e) => {
        this.setState({id: e.target.value});
    }


    saveKutups = async () => {
        const payload = {
            kutuphaneAd: this.state.name,
            adres_id: this.state.id
        }
        await Kutuphane.saveKutups(payload)
        this.setState({
            name: "",
            id: ""
        })
    }

    render() {
        const header = [
            {title: 'Kütüphane Id', prop: 'kutuphaneId'},
            {title: 'kutuphaneAd', prop: 'kutuphaneAd'},
            {title: 'sokak', prop: 'sokak'},
            {title: 'cadde', prop: 'cadde'},
            {title: 'kitapAdi', prop: 'kitapAdi'},
            {title: 'adet', prop: 'adet'},
        ];


        return (
            <GridWrapper>
                <h2>Kutuphaneler</h2>
                <Datatable tableHeaders={header} tableBody={this.state.kutups}/>
                <form style={{marginLeft: 80, width: 300}}>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputEmail1">Kütüphane Adı</label>
                        <input className="form-control" id="email" placeholder="Kütüphane Adı"
                               value={this.state.name}
                               onChange={this.handleNameChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Adres Id</label>
                        <input className="form-control" id="password" placeholder="Adres Id" value={this.state.id} onChange={this.handleIdChange}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.saveKutups}>
                        Ekle
                    </button>
                </form>

            </GridWrapper>
        )
    }
}
