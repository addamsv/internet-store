export const enum EUserRoles {
  ADMIN = "ROLE_ADMIN",
  GUEST = "ROLE_GUEST",
  USER = "ROLE_USER",
  MANAGER = "ROLE_MANAGER",
}

export interface IUser {
  id: number;
  name: string;
  iat?: string;
  roles?: EUserRoles[];
  enabled?: boolean;
}

export interface IUserData {
  user: IUser;
  token: string;
  avatar?: string;

  roles?: EUserRoles[]
}

export interface IUserSchema {
  authData?: IUserData;

  isAuthDataMounted?: boolean;
}
