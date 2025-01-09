import { useAtomValue } from "jotai";
import { jwtTokenAtom } from "./redux/store";
import { Navigate } from "react-router-dom";
import { useEffect, useState } from "react";

function PrivateRoute({ element }) {
    const jwtToken = useAtomValue(jwtTokenAtom);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setIsLoading(false);
    }, [])

    if (isLoading) {
        return <> Loading... </>
    }

    return jwtToken ? element : <Navigate to="/" replace />;
}

export default PrivateRoute;