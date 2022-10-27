<script setup>
import { reactive, ref, h } from 'vue';
import { NButton, useNotification } from "naive-ui";
import { listCoursesBySid, listCourses, listCoursesByQuery, selectCourse, cancelCourse } from '@/api'

const query = reactive({
    sid: "",
    name: ""
})

const allCourses = ref([])
const myCourses = ref([])

const columns1 = [
    {
        title: "id",
        key: "id",
        className: "_id"
    },
    {
        title: "课号",
        key: "cid"
    },
    {
        title: "课名",
        key: "name"
    },
    {
        title: "学分",
        key: "credit"
    },
    {
        title: "先行课",
        key: "fcid"
    }, {
        title: '操作',
        key: 'operate',
        render(row) {
            return h(
                NButton,
                {
                    ghost: true,
                    size: 'small',
                    type: "info",
                    onClick: () => handleSelect(row)
                },
                { default: () => '选择' }
            )
        }
    }
]
const columns2 = [
    {
        title: "id",
        key: "id",
        className: "_id"
    },
    {
        title: "课号",
        key: "cid"
    },
    {
        title: "课名",
        key: "name"
    },
    {
        title: "学分",
        key: "credit"
    },
    {
        title: "先行课",
        key: "fcid"
    }, {
        title: '操作',
        key: 'operate',
        render(row) {
            return h(
                NButton,
                {
                    ghost: true,
                    size: 'small',
                    type: "error",
                    onClick: () => handleCancel(row)
                },
                { default: () => '退选' }
            )
        }
    }
]

const getData = () => {
    listCourses().then(res => {
        allCourses.value = res.data
    })
    if (query.sid.length) {
        listCoursesBySid(query.sid).then(res => {
            myCourses.value = res.data
        })
    }

}
getData()

//查询课程
const searchMyCourse = () => {
    listCoursesBySid(query.sid).then(res => {
        myCourses.value = res.data
    })
}
const searchAllCourse = () => {
    listCoursesByQuery(query).then(res => {
        allCourses.value = res.data
    })

}
//选课和退选
const notification = useNotification();

const handleSelect = (row) => {
    selectCourse(query.sid, row.cid).then(res => {
        notification.success({
            content: "操作成功",
            meta: "success",
            duration: 1000,
            keepAliveOnHover: true
        });
        getData()
    })
}

const handleCancel = (row) => {
    cancelCourse(query.sid, row.cid).then(res => {
        notification.success({
            content: "操作成功",
            meta: "success",
            duration: 1000,
            keepAliveOnHover: true
        });
        getData()
    })
}
</script>

<template>
    <div class="container">
        <div class="left">
            <h3 class="title">所有课程</h3>
            <div class="query">
                <n-form ref="formRef" inline :label-width="80" :model="query">
                    <n-form-item>
                        <n-input placeholder="输入课名" v-model:value="query.name" />
                    </n-form-item>
                    <n-form-item>
                        <n-button attr-type="button" @click="searchAllCourse">
                            查询
                        </n-button>
                    </n-form-item>
                </n-form>

            </div>
            <div class="table">
                <!-- 表格 -->
                <n-data-table :columns="columns1" :data="allCourses" :pagination="false" :bordered="false" />
            </div>
        </div>
        <div class="right">
            <h3 class="title">我的课程</h3>
            <div class="query">
                <n-form ref="formRef" inline :label-width="80" :model="query">
                    <n-form-item>
                        <n-input placeholder="输入学号" v-model:value="query.sid" />
                    </n-form-item>
                    <n-form-item>
                        <n-button attr-type="button" @click="searchMyCourse">
                            查询
                        </n-button>
                    </n-form-item>
                </n-form>

            </div>
            <div class="table">
                <!-- 表格 -->
                <n-data-table :columns="columns2" :data="myCourses" :pagination="false" :bordered="false" />
            </div>
        </div>

    </div>
</template>

<style lang="less" scoped>
.container {
    display: flex;
    height: 100%;

    padding: 10px;

    .left {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 50%;
        height: 100%;
        padding: 10px;

        .query {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .title {
            color: rgb(93, 94, 93);
        }
    }

    .right {
        border-left: 1px rgb(193, 187, 187) solid;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 50%;
        height: 100%;
        padding: 10px;

        .query {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .title {
            color: rgb(93, 94, 93);
        }
    }
}
</style>