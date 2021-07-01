import request from "./request"

function getAll() {
    return request({
        url: `/yazarlar`,
        method: 'GET',
        data: null
    });
}


function getAllBooks(yazarId) {
    return request({
        url: `/yazarlar/kitaps/` + yazarId,
        method: 'GET',
        data: null
    });
}

function saveYazar(content){
    return request({
        url: `/yazarlar/create`,
        method: 'POST',
        data: content
    });
}

export const YazarlarService = {
    getAll, getAllBooks, saveYazar
}