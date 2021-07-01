import request from "./request"

function getAll() {
    return request({
        url: '/kutups',
        method: 'GET',
        data: null
    });
}

export const Kutuphane = {
    getAll, saveKutups
}


function saveKutups(content) {
    return request({
        url: '/kutups',
        method: 'POST',
        data: content
    });

}
