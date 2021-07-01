import axios from 'axios'

const client = axios.create({
    baseURL: "http://localhost:8080",
});

const onSuccess = function (response) {
    return response.data;
}

const onError = function (error) {
    return Promise.reject(error.response || error.message);
}

export const request = async function (options) {
    console.log(options)
    return client(options)
        .then(onSuccess)
        .catch(onError);
}

export default request;
