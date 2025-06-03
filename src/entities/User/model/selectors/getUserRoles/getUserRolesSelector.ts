import { createSelector } from "@reduxjs/toolkit";
import { IStateSchema } from "resources/store/StoreProvider";
import { EUserRoles } from "../../consts";

export const getUserRolesSelector = (state: IStateSchema) => state.user.authData?.user.roles;

export const isUserAdminSelector = createSelector(getUserRolesSelector, (roles) => (
  Boolean(roles?.includes(EUserRoles.ADMIN))
));

export const isUserManagerSelector = createSelector(getUserRolesSelector, (roles) => (
  Boolean(roles?.includes(EUserRoles.MANAGER))
));

export const isUserUserSelector = createSelector(getUserRolesSelector, (roles) => (
  Boolean(roles?.includes(EUserRoles.USER))
));
