<template>
  <div>
    <h1 class="title">AutoComplete Me</h1>
    <input type="text" id="searchBox" class="search-field" v-model="input" @keyup="search" autoFocus />
    <ul v-if="showResults" id="searchResults" class="term-list">
      <li v-for="result in filteredResults" :key="result">{{ result }}</li>
      <li v-if="noResults">Whoah! <strong>{{ input }}</strong> is not in the index. <br><small><a :href="'https://google.com/search?q=' + encodeURIComponent(input)">Try Google?</a></small></li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '',
      searchIndex: ["김싸피", "404 Error","Address Bar","Ajax","Apache","Autoresponder","BitTorrent","Blog","Bookmark","Bot","Broadband","Captcha","Certificate","Client","Cloud","Cloud Computing","CMS","Cookie","CSS","Cyberspace","Denial of Service","DHCP","Dial-up","DNS Record","Domain Name","Download","E-mail","Facebook","FiOS","Firewall","FTP","Gateway","Google","Google Drive","Gopher","Hashtag","Hit","Home Page","HTML","HTTP","HTTPS","Hyperlink","Hypertext","ICANN","Inbox","Internet","InterNIC","IP","IP Address","IPv4","IPv6","IRC","iSCSI","ISDN","ISP","JavaScript","jQuery","Meta Search Engine","Meta Tag","Minisite","Mirror","Name Server","Packet","Page View","Payload","Phishing","POP3","Protocol","Scraping","Search Engine","Social Networking","Socket","Spam","Spider","Spoofing","SSH","SSL","Static Website","Twitter","XHTML"],
      showResults: false
    };
  },
  computed: {
    filteredResults() {
      const inputTerms = this.input.toLowerCase().split(' ');
      const searchTerms = inputTerms[inputTerms.length - 1];

      return this.searchIndex.filter(term => term.toLowerCase().includes(searchTerms));
    },
    noResults() {
      return this.filteredResults.length === 0 && this.input.length > 0;
    }
  },
  methods: {
    search() {
      this.showResults = this.input.length > 0;
    }
  }
};
</script>

<style>
/* Your CSS styles */
</style>
