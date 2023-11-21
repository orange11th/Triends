<script setup>

import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import BoardListItem from "@/components/board/item/BoardListItem.vue";

const router = useRouter();
const articles = ref([]);
onMounted(() => {
  getArticleList();
});
const memberStore = useMemberStore();

const { userLogout, getUserInfo } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);


// computed 속성을 사용하여 userInfo가 있으면 userId를, 없으면 null을 반환
const userId = computed(() => {
  return userInfo.value ? userInfo.value.userId : "비회원";
});

const getArticleList = () => {
  listArticle(({ data }) => {
    articles.value = data.articles;
  },
    (error) => {
    console.log(error)
    }
  );
};

const moveWrite = () => {
  router.push({ name: "article-write" });
};

// 삭제 이벤트 핸들러
const handleDeleteArticle = (articleNo) => {
  // 해당하는 글 번호를 가진 아이템을 배열에서 제거
  articles.value = articles.value.filter(article => article.no !== articleNo);
};

</script>

<template>
 <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">트렌즈 여행기록</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="row align-self-center mb-2">
          <div class="col-md-2 text-start">
            <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
              글쓰기
            </button>
          </div>
        </div>
        <div class="board-list">
                <BoardListItem
                  v-for="article in articles"
                  :key="article.no"
                  :article="article"
                  :userId="userId"
                  @delete="handleDeleteArticle"
                ></BoardListItem>
        </div>
      </div>
    </div>
  </div>

  
</template>

<style scoped>
  .board-list {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>