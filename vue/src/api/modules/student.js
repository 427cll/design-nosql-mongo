import request from '../request';
export function listStudents() {
    return request({
        url: '/students',
        method: 'GET',
    })
}
export function listStudentsByQuery(query) {
    return request({
        url: "/students/query",
        method: "POST",
        data: query
    })
}


export function saveStudent(data) {
    return request({
        url: '/students',
        method: 'POST',
        data: data
    })
}







