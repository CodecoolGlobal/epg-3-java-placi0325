import { createContext, useCallback, useContext, useEffect, useState } from "react";

export const ClientContext = createContext();

export const ClientContextProvider = ( { children } ) => {
    const [client, setClient] = useState(undefined);

    function login(client) {
        setClient(client);
    }

    const chechAuthState = useCallback(function () {
        //read token from local storage, send req to check auth, save to state
        //useEffect to make it authomatic
        //set null or something!
    }, [])

    useEffect(function () {
        chechAuthState()
    }, [chechAuthState])
    

 return (
    <ClientContext.Provider value={{ client, login }}>{children}</ClientContext.Provider>
 )
}

export const useClient = () => {
    return useContext(ClientContext)
}