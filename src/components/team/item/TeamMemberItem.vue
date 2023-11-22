<script setup>
import { ref, onMounted, reactive } from "vue";
import { useRouter, useRoute } from "vue-router";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

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

function inviteTeam(teamId, userId) {
  teamInvite(
    teamId,
    userInfo.value.userId,
    userId,
    () => {
      alert(`${userId} 초대 완료!`);
    },
    (error) => {
      alert("이미 초대한 사용자입니다.");
    }
  );
}

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
</script>

<template>
  <div v-if="showTemplate">
    <div class="team">
      <div class="team-top">
        <div class="team-info">
          <img src="@/assets/img/icon/users.svg" alt="팀" />
          <h2 class="team-name">{{ props.team.teamName }} ·</h2>
          <a id="to-jitsi" href="#">
            <img id="chat" src="@/assets/img/icon/chat.svg" alt="영통" />
          </a>
        </div>
        <div class="team-plan">
          <a @click="movePlan(props.team.teamId)" href="#">
            <img class="clickable-img" src="@/assets/img/icon/go.svg" alt="" />
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
        <a class="leave-btn" @click="leave(props.team.teamId)" href="">
          <img src="@/assets/img/icon/out.svg" alt="" />
        </a>
      </div>
    </div>
    <!-- 모달 구현 -->
    <div v-show="modalState" class="modal-overlay" @click="closeModal">
      <div v-show="modalState" class="modal" @click.stop>
        <h1>teamId: {{ props.team.teamId }}</h1>
        <ul>
          <li v-for="(invite, index) in inviteList" :key="index">
            <span>{{ invite.userName }}</span>
            <a
              href=""
              @click.prevent="inviteTeam(props.team.teamId, invite.userId)"
              >({{ invite.userId }})</a
            >
          </li>
        </ul>
        <button class="close-button" @click="closeModal">닫기</button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
