<script setup>
import { ref, h } from 'vue'
import { RouterView, RouterLink } from 'vue-router'
import { NIcon } from "naive-ui";
import { BookmarkOutline, CaretDownOutline } from "@vicons/ionicons5";

const menuOptions = [
  {
    label: () => h(
      RouterLink,
      {
        to: {
          name: "student"
        }
      },
      { default: () => "学生管理" }
    ),
    key: 'student',
  },
  {
    label: () => h(
      RouterLink,
      {
        to: {
          name: "teacher"
        }
      },
      { default: () => "教师管理" }
    ),
    key: 'teacher',
  },
  {
    label: () => h(
      RouterLink,
      {
        to: {
          name: "course"
        }
      },
      { default: () => "课程管理" }
    ),
    key: 'course',
  },
]

const collapsed = ref(false)
const activeKey = ref("student")


const paths = ["/student", "/select-course"]
const href = window.location.href
let currPath = href.substring(href.lastIndexOf("/"))
if (currPath == '/student') activeKey.value = "student"
else if (currPath == '/teacher') activeKey.value = "teacher"
else if (currPath == '/course') activeKey.value = "course"

function renderMenuIcon(option) {
  return h(NIcon, null, { default: () => h(BookmarkOutline) });
}
function expandIcon() {
  return h(NIcon, null, { default: () => h(CaretDownOutline) });
}

</script>
<template>
  <n-space vertical>
    <n-layout has-sider>
      <n-layout-sider bordered collapse-mode="width" :collapsed-width="64" :width="240" :collapsed="collapsed"
        show-trigger @collapse="collapsed = true" @expand="collapsed = false">
        <n-menu v-model:value="activeKey" :collapsed="collapsed" :collapsed-width="64" :collapsed-icon-size="22"
          :options="menuOptions" :render-icon="renderMenuIcon" :expand-icon="expandIcon" />
      </n-layout-sider>
      <n-layout>
        <RouterView />
      </n-layout>
    </n-layout>
  </n-space>
</template>