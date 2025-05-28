import { EUserRoles, getUserAuthData, getUserRolesSelector } from "entities/User";
import { useMemo } from "react";
import { useSelector } from "react-redux";
import { Navigate, useLocation } from "react-router-dom";
import { RoutePath } from "resources/router/routeConfig/routeConfig";

interface IRequireAuth {
  children: JSX.Element;
  requiredRoles?: EUserRoles[];
}

export function RequireAuth({ children, requiredRoles }: IRequireAuth) {
  const auth = useSelector(getUserAuthData);

  const userRoles = useSelector(getUserRolesSelector);

  const isUserAuthorized = useMemo(() => {
    if (!requiredRoles) {
      return true;
    }

    return requiredRoles.some((role) => userRoles?.includes(role));
  }, [requiredRoles, userRoles]);

  const location = useLocation();

  if (!auth) {
    // redirect
    // return <Navigate to={RoutePath.main} state={{ from: location }} replace />;
    return <Navigate to={RoutePath.books} state={{ from: location }} replace />;
  }

  if (!isUserAuthorized) {
    // redirect
    // return <Navigate to={RoutePath.main} state={{ from: location }} replace />;
    return <Navigate to={RoutePath.forbidden} state={{ from: location }} replace />;
  }

  return children;
}
