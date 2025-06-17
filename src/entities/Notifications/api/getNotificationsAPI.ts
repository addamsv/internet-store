import { RTK } from "resources/lib/restApi/RTK";
import { INotificationsItem } from "../types";

interface ICustomRetNotificationsData {
  isSuccess: boolean;
  statusCode: number;
  message: string;
  data: INotificationsItem[];
}

const api = RTK.injectEndpoints({
  endpoints: (build) => ({
    getNotifications: build.query<ICustomRetNotificationsData, undefined>({
      query: () => ({
        url: "/notifications"
      })
    })
  })
});

export const useGetNotificationsAPI = api.useGetNotificationsQuery;
