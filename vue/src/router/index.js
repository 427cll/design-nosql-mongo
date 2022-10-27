import { createRouter, createWebHistory } from "vue-router";
import Manage from "../views/Manage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/student"
    }, {
      path: "/",
      name: "manage",
      component: Manage,
      children: [
        {
          path: "/student",
          name: "student",
          component: () => import("../views/Student.vue"),
        },
        {
          path: "/teacher",
          name: "teacher",
          component: () => import("../views/Teacher.vue"),
        },
        {
          path: "/course",
          name: "course",
          component: () => import("../views/Course.vue"),
        }
      ]
    },
    {
      path: "/select-course",
      name: "select-course",
      component: () => import("@/views/SelectCourse.vue")
    },
  ],
});

export default router;
