<template>
  <div>
    <login-form v-if="!oidc.accessToken" v-bind:server-url="oidc.url" v-bind:client-id="oidc.clientId" v-bind:secret="oidc.secret" v-on:logged-in="oidc.onLoggedIn"></login-form>
    <div v-else>
      <span>Made with Vue <img src="./logo.png" class="icon" /></span>
      <h1>Hello!</h1>
      <h2>People - Public</h2>
      <div v-for="(person, index) in people" v-bind:key="index">
        {{person}}
      </div>
      <h2>Admin - Protected (needs admin role)</h2>
      <div>
        {{admin}}
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive, computed, watch } from "vue";
import LoginForm from "./Login.vue";

export default {
  components: {LoginForm},
  setup() {
    let oidc = useOpenIDConnect();
    let people = ref([]);
    let admin = ref(null);
    let url = ""

    watch(oidc.accessToken,value => {
      fetch("http://localhost:8080/person")
              .then(response => response.json())
              .then((json) => people.value = json);

      fetch("http://localhost:8080/admin", {
        headers: new Headers({ ...oidc.authHeader.value })
      })
              .then(response => response.json())
              .then((json) => admin.value = json);
    });

    return {
      oidc,
      people,
      admin
    };
  }
};

function useOpenIDConnect() {
  let url = ref( "http://localhost:8080/login");
  let clientId = ref( "backend-service");
  let secret = ref( "secret");
  let accessToken = ref( "");
  let authHeader = computed(() => {return {"Authorization": `Bearer ${accessToken.value}`}});
  let onLoggedIn = (event) => {
    accessToken.value = event.access_token;
  };

  return {
    url,
    clientId,
    secret,
    accessToken,
    authHeader,
    onLoggedIn
  }
}

</script>

<style scoped>
.icon {
  display: inline-block;
  height:2em;
  margin: 0;
  padding: 0;
  vertical-align: middle;
}
* {
  font-family: Arial, Helvetica, sans-serif;
}
</style>
