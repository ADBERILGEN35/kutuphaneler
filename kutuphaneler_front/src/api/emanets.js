import request from "./request"

function getAll() {
    return request({
        url: `/tblemanet/pretty`,
        method: 'GET',
        data: null
    });
}

function saveEmanet(content) {
    return request({
        url: `/tblemanet`,
        method: 'POST',
        data: content
    });
}

function updateEmanet(content) {
    return request({
        url: `/tblemanet/` + content.emanetId,
        method: 'PUT',
        data: content
    });
}

function getById(emanetId) {
    return request({
        url: `/tblemanet/` + emanetId,
        method: 'GET',
        data: null
    });
}

function deleteById(emanetId) {
    return request({
        url: '/tblemanet/' + emanetId,
        method: 'DELETE',
        data: null
    });
}


export const Emanet = {
    getAll, saveEmanet, getById, updateEmanet, deleteById
}