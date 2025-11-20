import 'pinia'

interface ImportMetaEnv {
  VITE_API_BASEURL: string
}

declare module 'pinia' {
  export interface DefineStoreOptionsBase<S, Store> {
    persist?: boolean | PersistedStateOptions | PersistedStateOptions[]
  }
}