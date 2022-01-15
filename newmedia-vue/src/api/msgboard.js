import axios from 'axios'

export let getMsgboard = (req) => {
    return axios.request({
        method: "GET",
        url: "/msgboard",
        params: req,
        data: ""
    })
}

export let deleteMsgboard = (row) => {
    return axios.request({
        method: "DELETE",
        url: "/msgboard",
        params: {
            id: parseInt(row.id)
        },
        data: ""
    })
}

export let addMsgboard = (form) => {
    return axios.request({
        method: "POST",
        url: "/msgboard",
        params: {
        },
        data: {
            name: form.field1,
            contact: form.field2,
            content: form.field3,
            date: form.field4,
            other: form.field5
        }
    })
}

export let updateMsgboard = (form, id) => {
    return axios.request({
        method: "PUT",
        url: "/msgboard",
        params: {
        },
        data: {
            id: id,
            name: form.field1,
            contact: form.field2,
            content: form.field3,
            date: form.field4,
            other: form.field5
        }
    })
}