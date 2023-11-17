import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/BoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        // {
        //   path: "view/:articleno",
        //   name: "article-view",
        //   component: () => import("@/components/board/BoardDetail.vue"),
        // },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        // {
        //   path: "modify/:articleno",
        //   name: "article-modify",
        //   component: () => import("@/components/board/BoardModify.vue"),
        // },
      ],
    },
    {
      path: "/team",
      name: "team",
      component: () => import("../views/TeamView.vue"),
      children: [
        {
          path: "list",
          name: "team-list",
          component: () => import("@/components/team/teamList.vue"),
        },
        {
          path: "plan",
          name: "plan-list",
          component: () => import("@/components/team/PlanList.vue"),
        },
      ],
    },
    {
      path: "/attraction",
      name: "attraction",
      component: () => import("../views/AttractionView.vue"),
      children: [
        {
          path: "list",
          name: "attraction-list",
          component: () => import("@/components/attraction/attractionList.vue"),
        },
        {
          path: "detail/:contentId",
          name: "attraction-detail",
          component: () => import("@/components/attraction/attractionDetail.vue"),
        },
      ],
    },
    {
      path: "/member",
      name: "member",
      component: () => import("../views/MemberView.vue"),
      children: [
        {
          path: "login",
          name: "member-login",
          component: () => import("@/components/member/memberLogin.vue"),
        },
        {
          path: "info",
          name: "member-info",
          component: () => import("@/components/member/memberInfo.vue"),
        },
        {
          path: "regist",
          name: "member-regist",
          component: () => import("@/components/member/memberRegist.vue"),
        },
      ],
    },
  ],
});

export default router;
