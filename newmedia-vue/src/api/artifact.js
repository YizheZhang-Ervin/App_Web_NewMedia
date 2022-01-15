import axios from 'axios'

export let getArtifact = (req) => {
    return axios.request({
        method: "GET",
        url: "/artifact",
        params: req,
        data: ""
    })
}

export let deleteArtifact = (row) => {
    return axios.request({
        method: "DELETE",
        url: "/artifact",
        params: {
            id: parseInt(row.id)
        },
        data: ""
    })
}

export let addArtifact = (form) => {
    return axios.request({
        method: "POST",
        url: "/artifact",
        params: {
        },
        data: {
            name: form.field1,
            description: form.field2,
            link: form.field3,
            date: form.field4,
            type: form.field5
        }
    })
}

export let updateArtifact = (form, id) => {
    return axios.request({
        method: "PUT",
        url: "/artifact",
        params: {
        },
        data: {
            id: id,
            name: form.field1,
            description: form.field2,
            link: form.field3,
            date: form.field4,
            type: form.field5
        }
    })
}