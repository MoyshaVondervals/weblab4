import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";

// Загружаем состояние из localStorage
const loadState = () => {
    try {
        const serializedState = localStorage.getItem("authState");
        if (serializedState === null) return undefined;
        return JSON.parse(serializedState);
    } catch (e) {
        console.error("Failed to load state from localStorage:", e);
        return undefined;
    }
};

const saveState = (state) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem("authState", serializedState);
    } catch (e) {
        console.error("Failed to save state to localStorage:", e);
    }
};

const store = configureStore({
    reducer: {
        auth: authReducer,
    },
    preloadedState: { auth: loadState() },
});

store.subscribe(() => {
    saveState(store.getState().auth);
});

export default store;
