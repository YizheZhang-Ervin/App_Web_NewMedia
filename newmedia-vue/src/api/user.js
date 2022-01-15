import axios from 'axios'

export let getUser = (req) => {
    return axios.request({
        method: "GET",
        url: "/user",
        params: req,
        data: ""
    })
}

export let deleteUser = (row) => {
    return axios.request({
        method: "DELETE",
        url: "/user",
        params: {
            id: parseInt(row.id)
        },
        data: ""
    })
}

export let addUser = (form) => {
    return axios.request({
        method: "POST",
        url: "/user",
        params: {
        },
        data: {
            name: form.field1,
            password: form.field2,
            authority: form.field3,
            job: form.field4,
            description: form.field5,
        }
    })
}

export let updateUser = (form, id) => {
    return axios.request({
        method: "PUT",
        url: "/user",
        params: {
        },
        data: {
            id: id,
            name: form.field1,
            password: form.field2,
            authority: form.field3,
            job: form.field4,
            description: form.field5,
        }
    })
}

export let userLoginCheck = (form) => {
    return axios.request({
        method: "post",
        url: "/user/check",
        data: form
    })
}