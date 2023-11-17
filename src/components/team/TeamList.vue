<script setup>
import { ref, onMounted } from "vue";
import { myTeamList } from "@/api/team";
import { useRouter } from "vue-router";

const router = useRouter();

const userId = "ssafy";

const teamList = ref([]);

function getTeamList() {
  myTeamList(
    //여기 나중에 로그인으로 바꿔야댐
    userId,
    ({ data }) => {
      teamList.value = data;
    },
    console.error()
  );
}

onMounted(() => {
  getTeamList();
});
</script>

<template>
  <div class="container">
    <h1 class="team-title">My team</h1>
    <ul class="team-list">
      <li v-for="team in teamList" :key="team.teamId" class="team-item">
        <div class="team-header">
          <h2 class="team-info">{{ team.teamName }}</h2>
        </div>
        <ul class="member-list">
          <a href="#" class="plan-link">여행계획 바로가기</a>
          <li
            v-for="(teamMember, index) in team.teamList"
            :key="teamMember.userId"
            class="member-item"
          >
            <span class="member-info">{{ teamMember.userId }}</span>
          </li>
          <li class="member-item"><a href="" class="invite-link">초대하기</a></li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.team-title {
  color: black;
  margin: 50px 100px;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.team-list {
  list-style: none;
  padding: 0;
}

.team-item {
  background-color: #72b85c;
  margin: 50px 100px;
  padding: 10px;
  border-radius: 70px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.invite-link {
  text-decoration: none;
  color: white;
}

.member-list {
  display: flex;
  /* justify-content: space-between; */
  align-items: center;
  background-color: #f0f0f0; /* 배경색 추가 */
  padding: 10px;
  margin: 0 50px;
  margin-bottom: 10px;
  border-radius: 70px; /* 모서리 둥글게 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
}

.team-info {
  color: white;
  text-align: center;
}

.team-info-container {
  display: flex; /* Flexbox 사용 */
  gap: 10px; /* 요소 사이 간격 */
}

.plan-link {
  background-color: #007bff;
  color: white;
  padding: 5px 10px;
  font-size: 20px;
  text-decoration: none;
  border-radius: 15px; /* 버튼 모서리 둥글게 */
  margin: 0 20px;
}

.member-item {
  background-color: #41a788;
  color: white;
  padding: 10px;
  border-radius: 50%; /* 원형 모양 */
  width: 100px; /* 고정 너비 */
  height: 100px; /* 고정 높이 */
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-size: 30px; /* 글자 크기 조정 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 0 20px;
}

.member-info {
  color: white;
}
</style>
