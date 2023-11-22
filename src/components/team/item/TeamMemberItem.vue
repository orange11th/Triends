<script setup>
import { ref, onMounted, reactive } from "vue";
import { useRouter, useRoute } from "vue-router";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import TeamInviteSearch from "./TeamInviteSearchItem.vue";

import "@/assets/css/team/teamMember.css";

const router = useRouter();
const route = useRoute();

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({
  team: Object,
});

function movePlan(teamId) {
  router.push({ name: "plan-list", params: { teamId: teamId } });
}

const showTemplate = ref(true);
const modalState = ref(false);

const openModal = (teamId) => {
  teamInviteList(
    teamId,
    ({ data }) => {
      inviteList.value = data;
    },
    console.error()
  );
  modalState.value = true;
};

const closeModal = () => {
  modalState.value = false;
};

const inviteList = ref([]);

function leave(teamId) {
  leaveTeam(
    teamId,
    userInfo.value.userId,
    () => {
      showTemplate.value = false;
    },
    console.error()
  );
}

function moveChat(teamId) {
  // console.log(props.team.teamList);
  router.push({
    name: "team-chat",
    params: { 
      teamId: teamId,
      // 'someData'는 간단한 데이터일 경우에만 추가 가능
      teamList: JSON.stringify(props.team.teamList),
    }
  });
};
</script>

<template>
  <div v-if="showTemplate">
    <div class="team">
      <div class="team-top">
        <div class="team-info">

          <h2 class="team-name">{{ props.team.teamName }} ·</h2>
          <a id="to-jitsi" href="#" @click="moveChat(props.team.teamId)">
            <img id="chat" src="@/assets/img/icon/chat.svg" alt="영통" />
          </a>
        </div>
        <div class="team-plan">
          <a @click="movePlan(props.team.teamId)" href="#">
            <img
              id="go"
              class="clickable-img"
              src="@/assets/img/icon/go.svg"
              alt=""
            />
          </a>
          
        </div>
      </div>
      <hr />
      <div class="team-calendar">여기다 달력모양넣을거임~</div>
      <hr />
      <div class="team-bottom">
        <a
          class="invite-btn"
          @click.prevent="() => openModal(props.team.teamId)"
          href=""
        >
          <img src="@/assets/img/icon/user-plus.svg" alt="" />
        </a>
        <a href="">
          <img id="users" src="@/assets/img/icon/users.svg" alt="팀" />
        </a>
        <a class="leave-btn" @click="leave(props.team.teamId)" href="">
          <img src="@/assets/img/icon/out.svg" alt="" />
        </a>
      </div>
    </div>

    <div v-show="modalState" class="modal-overlay" @click="closeModal">
      <div v-show="modalState" class="modal" @click.stop>
        <TeamInviteSearch :inviteList="inviteList" :team="team" />
        <button class="close-button" @click="closeModal">닫기</button>
      </div>
    </div>
  </div>

  <!-- <li
    v-for="teamMember in props.team.teamList"
    :key="teamMember.userId"
    class="member-item"
  >
    <span class="member-info"
      >{{ teamMember.userName }}<br />{{ teamMember.userId }}</span
    >
  </li>
  <li class="member-item">
    <a href="#" class="invite-link" @click="() => openModal(props.team.teamId)"
      >초대하기</a
    >
  </li> -->
</template>

<style scoped></style>
