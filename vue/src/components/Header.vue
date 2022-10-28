<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const paths = ["/student", "/select-course"]

const router = useRouter()
const href = window.location.href
let currPath = href.substring(href.lastIndexOf("/"))
const activeIndex = ref(0)
if (currPath == '/') currPath = '/student'
if (currPath == '/student' || currPath == '/teacher' || currPath == '/course') activeIndex.value = 0
else activeIndex == 1

const changeIndex = (index) => {
    activeIndex.value = index
    router.push(paths[index])
}

</script>

<template>
    <div class="container">
        <div :class="{ tab: true, active: activeIndex == 0 }" @click="changeIndex(0)">
            <span class="tab-text">管理</span>
            <span class="border"></span>
        </div>
        <div class="space"></div>
        <div :class="{ tab: true, active: activeIndex == 1 }" @click="changeIndex(1)">
            <span class="tab-text">选课</span>
            <span class="border"></span>
        </div>
    </div>
</template>

<style lang="less" scoped>
.container {
    border-bottom: 1px rgb(239, 239, 245) solid;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: flex-end;
    padding: 20px;

    .tab {
        border-bottom: 2px white solid;

        .tab-text {
            font-size: 20px;
            font-weight: bold;
        }
    }

    .active {
        color: #18A058;
        border-bottom: 2px #18A058 solid;
    }

    .space {
        width: 5%;
    }
}
</style>