<script setup>
import { ref, computed, watch } from "vue";
import { teamInviteList, teamInvite, leaveTeam } from "@/api/team";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({
  inviteList: Array,
  team: Object,
});

const input = ref("");
const showResults = ref(false);

// userId와 userName을 결합하여 검색 인덱스를 생성합니다.
const searchIndex = computed(() => {
  return props.inviteList.map((item) => ({
    label: `${item.userId}(${item.userName})`,
    userId: item.userId,
  }));
});

const filteredResults = computed(() => {
  const inputTerms = input.value.toLowerCase().split(" ");
  const searchTerms = inputTerms[inputTerms.length - 1];

  return searchIndex.value.filter((item) =>
    item.label.toLowerCase().includes(searchTerms)
  );
});

const noResults = computed(() => {
  return filteredResults.value.length === 0 && input.value.length > 0;
});

function search() {
  showResults.value = input.value.length > 0;
}

watch(input, () => {
  search();
});

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
</script>


<template>
  <div>
    <h1>{{ team.teamId }}</h1>
    <h1 class="title">팀원 찾기</h1>
    <input
      type="text"
      class="search-field"
      v-model="input"
      @keyup="search"
      autoFocus
    />
    <ul v-if="showResults" class="term-list">
      <li>
        <a
          v-for="result in filteredResults"
          :key="result.userId"
          href="#"
          @click.prevent="inviteTeam(props.team.teamId, result.userId)"
          >{{ result.label }}</a
        >
      </li>
      <li>
        <a v-if="noResults"> 그런 아이디는 없어요 ㅠㅠ </a>
      </li>
    </ul>
  </div>
</template>

<style scope>

.title {
  width: 100%;
  margin: 3em 0 1em;
  text-align: center;
  font-family: "Arvo", "Helvetica Neue", Helvetica, arial, sans-serif;
  font-size: 170%;
  font-weight: 400;
  color: #2a5ba3;
  text-shadow: 1px 1px 0px #fff, 2px 2px #ddd, 3px 3px 1px #ddd;
}

.search-field {
  display: block;
  width: 200px;
  margin: 1em auto 0;
  padding: 0.5em 10px;
  border: 1px solid #999;
  font-size: 130%;
  font-family: "Arvo", "Helvetica Neue", Helvetica, arial, sans-serif;
  font-weight: 400;
  color: #3e8ce0;
  border-radius: 3px;
}

.term-list {
  list-style: none inside;
  width: 30%;
  margin: 0 auto 2em;
  padding: 5px 10px 0;
  text-align: left;
  color: #777;
  background: #fff;
  border: 1px solid #ddd;
  font-family: "Arvo", "Helvetica Neue", Helvetica, arial, sans-serif;
  font-weight: 400;
  border-radius: 3px;
}

.term-list li {
  padding: 0.5em 0;
  border-bottom: 1px solid #eee;
}

.term-list strong {
  color: #444;
  font-weight: 700;
}

.hidden {
  display: none;
}
</style>
