import request from '../request';

export function selectCourse(sid,cid) {
    return request({
        url: `/select-course/sid/${sid}/cid/${cid}`,
        method: "POST"
    })
}

export function cancelCourse(sid,cid) {
    return request({
        url: `/select-course/sid/${sid}/cid/${cid}`,
        method: "DELETE"
    })
}
