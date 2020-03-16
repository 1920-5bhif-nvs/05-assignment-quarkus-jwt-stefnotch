<template>
    <div>
        <p>Login to server: {{serverUrl}}</p>
        <input type="text" v-model="state.username">
        <br>
        <input type="password" v-model="state.password">
        <br>
        <button @click="tryLogin">Login</button>
    </div>
</template>

<script>
    import { ref, reactive, computed } from "vue";

export default {
    name: "login-form",
    props: {
        serverUrl: String,
        clientId: String,
        secret: String
    },
    setup(props, context) {
        const state = reactive({
            username: "",
            password: ""
        });

        const tryLogin = () => {
            let form = new URLSearchParams();
            form.set("username", state.username);
            form.set("password", state.password);
            form.set("grant_type", "password");

            fetch(props.serverUrl, {
                method: "POST",
                body: form
            })
                .then(response => response.json())
                .then(json => context.emit("logged-in", json));
        };

        return {
            state,
            tryLogin
        };
    }
};
</script>

<style scoped>
* {
    font-family: Arial, Helvetica, sans-serif;
}
</style>
