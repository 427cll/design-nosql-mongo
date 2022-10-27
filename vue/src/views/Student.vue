<template>
  <div class="container">
    <!-- 表格上方 -->
    <div class="table-top">
      <div class="search-box">
        <n-form ref="formRef" inline :label-width="80" :model="query">
          <n-form-item label="年龄下限" path="query.ageStart">
            <n-input placeholder="输入年龄下限" v-model:value="query.ageStart" />
          </n-form-item>
          <n-form-item label="年龄上限" path="query.ageEnd">
            <n-input placeholder="输入年龄上限" v-model:value="query.ageEnd" />
          </n-form-item>
          <n-form-item label="学院" path="query.dname">
            <n-input placeholder="输入学院 " v-model:value="query.dname" />
          </n-form-item>
          <n-form-item>
            <n-button attr-type="button" @click="handleSearch">
              查询
            </n-button>
          </n-form-item>
        </n-form>
      </div>
      <div class="add-box">
        <n-button strong secondary type="primary" style="margin-right:2px" @click="handleAdd">
          新增
          <n-icon>
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512">
              <path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"
                d="M256 112v288"></path>
              <path fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"
                d="M400 256H112"></path>
            </svg>
          </n-icon>
        </n-button>
        <n-upload action="http://localhost:8080/excel/upload/student" @finish="handleFinish"
          file-list-style="display:none">
          <n-button strong secondary type="primary">
            批量导入
            <n-icon>
              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 16 16">
                <g fill="none">
                  <path
                    d="M3.5 13h9a.75.75 0 0 1 .102 1.493l-.102.007h-9a.75.75 0 0 1-.102-1.493L3.5 13h9h-9zM7.898 1.007L8 1a.75.75 0 0 1 .743.648l.007.102v7.688l2.255-2.254a.75.75 0 0 1 .977-.072l.084.072a.75.75 0 0 1 .072.977l-.072.084L8.53 11.78a.75.75 0 0 1-.976.073l-.084-.073l-3.536-3.535a.75.75 0 0 1 .977-1.133l.084.072L7.25 9.44V1.75a.75.75 0 0 1 .648-.743L8 1l-.102.007z"
                    fill="currentColor"></path>
                </g>
              </svg>
            </n-icon>
          </n-button>
        </n-upload>
      </div>
    </div>

    <!-- 表格 -->
    <n-data-table :columns="columns" :data="data" :pagination="false" :bordered="false" />

    <!-- 表格下方 -->
    <div class="table-bottom">
    </div>
  </div>

  <!-- modal -->
  <n-drawer v-model:show="editVisible" :default-width="280" resizable :placement="placement">
    <n-drawer-content title="修改/新增" closable>
      <n-form ref="formRef" :model="form" label-placement="left" :label-width="70" :style="{
        maxWidth: '280px'
      }">
        <n-form-item label="学号">
          <n-input placeholder="" v-model:value="form.sid" />
        </n-form-item>
        <n-form-item label="姓名">
          <n-input placeholder="" v-model:value="form.name" />
        </n-form-item>
        <n-form-item label="性别">
          <n-input placeholder="" v-model:value="form.sex" />
        </n-form-item>
        <n-form-item label="年龄">
          <n-input placeholder="" v-model:value="form.age" />
        </n-form-item>
        <n-form-item label="出生日期">
          <n-input placeholder="" v-model:value="form.birthday" />
        </n-form-item>
        <n-form-item label="院系">
          <n-input placeholder="" v-model:value="form.dname" />
        </n-form-item>
        <n-form-item label="班级">
          <n-input placeholder="" v-model:value="form.clas" />
        </n-form-item>
        <div style="display: flex; justify-content: flex-end">
          <n-button round type="primary" @click="submitForm">
            保存
          </n-button>
        </div>

      </n-form>
    </n-drawer-content>
  </n-drawer>
</template>

<script setup>
import { reactive, ref, h } from "vue"
import { NButton } from "naive-ui";
import { listStudentsByQuery, saveStudent } from "@/api"
import { useNotification } from "naive-ui";

const query = reactive({
  dname: "",
  ageStart: "",
  ageEnd: ""
})

const data = ref([]);

const handleSearch = () => {
  listStudentsByQuery(query).then(res => {
    data.value = res.data
  })
}

const getData = () => {
  handleSearch()
}
getData()
const columns = [
  {
    title: "id",
    key: "id",
    className: "_id"
  },
  {
    title: "学号",
    key: "sid"
  },
  {
    title: "姓名",
    key: "name"
  },
  {
    title: "性别",
    key: "sex"
  },
  {
    title: "年龄",
    key: "age"
  },
  {
    title: "出生日期",
    key: "birthday"
  },
  {
    title: "院系",
    key: "dname"
  },
  {
    title: "班级",
    key: "clas"
  }, {
    title: '操作',
    key: 'update',
    render(row) {
      return h(
        NButton,
        {
          ghost: true,
          size: 'small',
          type: "info",
          onClick: () => handleEdit(row)
        },
        { default: () => 'Edit' }
      )
    }
  }
]

// 对话框
let form = reactive({
  id: "",
  sid: "",
  name: "",
  sex: "",
  age: "",
  clas: "",
  birthday: "",
  dname: ""
})

const editVisible = ref(false);
const placement = ref("left");
const handleEdit = (row) => {
  form.id = ""
  Object.keys(form).forEach((item) => {
    form[item] = row[item];
  });
  editVisible.value = true;
}

const handleAdd = () => {
  delete form.id
  Object.keys(form).forEach((item) => {
    form[item] = "";
  });
  editVisible.value = true
}

const notification = useNotification();

const submitForm = () => {
  editVisible.value = false;

  saveStudent(form).then(res => {
    notification.success({
      content: "操作成功",
      meta: "success",
      duration: 1000,
      keepAliveOnHover: true
    });
    getData()
  })
}

//Excel上传回调
const handleFinish = () => {
  getData()
};

async function beforeUpload(data) {
  // if (data.file.file?.type !== "image/png") {
  //   console.log("not support")
  //   return false;
  // }
  return true;
}
</script>

<style lang="less" scoped >
.container {
  .up-file-list {
    display: none;
  }

  padding: 20px;

  .table-top {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;

    .add-box {
      margin-bottom: 5px;
      display: flex;


    }
  }

  ._id {
    display: none;
  }

  .control {
    text-align: center;
    margin: 10px;
  }
}
</style>