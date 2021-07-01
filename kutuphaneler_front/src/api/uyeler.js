import request from "./request"

function getAll() {
    return request({
        url: '/tbluyeler',
        method: 'GET',
        data: null
    });
}

function saveUye(content) {
    return request({
        url: '/tbluyeler',
        method: 'POST',
        data: content
    });
}

export const UyelerService = {
    getAll, saveUye
}
