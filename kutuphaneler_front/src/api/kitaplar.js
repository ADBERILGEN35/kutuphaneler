import request from "./request"

function getAll() {
    return request({
        url:    `/kitaplar`,
        method: 'GET',
        data: null
    });
}

function saveKitap(content) {
    return request({
        url:    `/kitaplar`,
        method: 'POST',
        data: content
    });
}

export const KitapService = {
    getAll, saveKitap
}