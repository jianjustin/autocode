export const {{.ApiFunc}} = (data) => {
    return service({
        url: '/updf/{{.ApiGroup}}/{{.ApiFunc}}',
        method: {{.ApiMethod}},
        data: data
    })
}