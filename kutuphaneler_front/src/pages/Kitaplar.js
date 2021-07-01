import React, {Component} from 'react';
import styled from 'styled-components';
import Datatable from 'react-bs-datatable';
import {KitapService} from "../api/kitaplar";

const GridWrapper = styled.div`
  display: grid;
  grid-gap: 10px;
  margin-top: 1em;
  margin-left: 6em;
  margin-right: 6em;
  grid-template-columns: repeat(12, 1fr);
  grid-auto-rows: minmax(25px, auto);
`;
export class Kitaplar extends Component {
    constructor(props){
        super(props);
        this.state = {
            kitaplar: [],
            isbn: "",
            kitapAdi: "",
            yayinTarihi: "",
            sayfaSayisi: "",
            yayinEviId : "",
            yazarId: ""
        }
    }

    async componentDidMount() {
        const results = await KitapService.getAll()
        this.setState({kitaplar: results})
    }
    handleYazarIdChange = (e) => {
        this.setState({yazarId: e.target.value});
    }
    handleYayinEviIdChange = (e) => {
        this.setState({yayinEviId: e.target.value});
    }
    handleSayfaSayisiChange = (e) => {
        this.setState({sayfaSayisi: e.target.value});
    }
    handleYayinTarihiChange = (e) => {
        this.setState({yayinTarihi: e.target.value});
    }
    handleKitapAdiChange = (e) => {
        this.setState({kitapAdi: e.target.value});
    }
    handleISBNChange = (e) => {
        this.setState({isbn: e.target.value});
    }
    saveKitap = async () => {
        const payload = {
            isbn: this.state.isbn,
            kitapAdi: this.state.kitapAdi,
            yayinTarihi: this.state.yayinTarihi,
            sayfaSayisi: this.state.sayfaSayisi,
            yayinEviId: this.state.yayinEviId,
            yazarId: this.state.yazarId
        }
        await KitapService.saveKitap(payload)
        this.setState({
            isbn: "",
            kitapAdi: "",
            yayinTarihi: "",
            sayfaSayisi: "",
            yayinEviId: "",
            yazarId: ""
        })
    }

    render(){
        const header = [
            { title: 'Kitap Id', prop: 'kitap_id' },
            { title: 'Kitap Adı', prop: 'kitapAdi' },
            { title: 'Yayın Tarihi', prop: 'yayinTarihi' },
            { title: 'Sayfa Sayısı', prop: 'sayfaSayisi' },
            { title: 'Yazar Adı', prop: 'yazarAd' },
            { title: 'Yazar Soyadı', prop: 'yazarSoyad' },
            { title: 'ISBN', prop: 'isbn' }
        ];


        return(
            <GridWrapper>
                <h2>Kitaplar</h2>
                <Datatable tableHeaders={header} tableBody={this.state.kitaplar} />
                <form style={{marginLeft: 80, width: 300}}>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputEmail1">Isbn</label>
                        <input className="form-control" placeholder="Isbn" value={this.state.isbn} onChange={this.handleISBNChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Kitap Adı</label>
                        <input className="form-control" placeholder="Kitap Adı" value={this.state.kitapAdi} onChange={this.handleKitapAdiChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Yayın Tarihi</label>
                        <input className="form-control" placeholder="Yayın Tarihi" value={this.state.yayinTarihi} onChange={this.handleYayinTarihiChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Sayfa Sayısı</label>
                        <input className="form-control" placeholder="Sayfa Sayısı" value={this.state.sayfaSayisi} onChange={this.handleSayfaSayisiChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Yayın Evi Id</label>
                        <input className="form-control" placeholder="Yayın Evi Id" value={this.state.yayinEviId} onChange={this.handleYayinEviIdChange}/>
                    </div>
                    <div className="form-group text-left">
                        <label htmlFor="exampleInputPassword1">Yazar Id</label>
                        <input className="form-control" placeholder="Yazar Id" value={this.state.yazarId} onChange={this.handleYazarIdChange}/>
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.saveKitap}>
                        Ekle
                    </button>
                </form>
            </GridWrapper>
        )
    }
}