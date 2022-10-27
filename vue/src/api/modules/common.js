import request from '../request';

export function listCoursesBySid(cid) {
    return request({
        url: `/courses/sid/${cid}`,
        method: "get"
    })
}

export function listCourses() {
    return request({
        url: "/courses",
        method: "get"
    })
}


export function listCoursesByQuery(query) {
    return request({
        url: "/courses/query",
        method: "post",
        data: query
    })
}