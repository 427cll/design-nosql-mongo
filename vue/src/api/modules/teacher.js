import request from '../request';

export function listTeachersByTid(tid) {
    return request({
        url: `/teachers/sid/${tid}`,
        method: "get"
    })
}

export function listTeachers() {
    return request({
        url: "/teachers",
        method: "get"
    })
}

export function listTeachersByQuery(query) {
    return request({
        url: "/teachers/query",
        method: "POST",
        data: query
    })
}

export function saveTeacher(data) {
    return request({
        url: '/teachers',
        method: 'post',
        data: data
    })
}







