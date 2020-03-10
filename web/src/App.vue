<template>
  <div>
    <login-form v-if="!oidc.accessToken" v-bind:server-url="oidc.url" v-bind:client-id="oidc.clientId" v-bind:secret="oidc.secret" v-on:logged-in="oidc.onLoggedIn"></login-form>
    <div v-else>
      <img src="./logo.png" />
      <h1>Hello Vue 3!</h1>
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

    return {
      oidc
    };
  }
};

function useOpenIDConnect() {
  let url = ref( "http://localhost:8080/login");
  let clientId = ref( "backend-service");
  let secret = ref( "secret");
  let accessToken = ref( "");
  let onLoggedIn = (event) => {
    accessToken.value = event.access_token;
  };

  return {
    url,
    clientId,
    secret,
    accessToken,
    onLoggedIn
  }
}

</script>

<style scoped>
img {
  width: 200px;
}
h1 {
  font-family: Arial, Helvetica, sans-serif;
}
</style>
