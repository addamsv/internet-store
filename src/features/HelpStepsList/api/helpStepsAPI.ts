import { RTK } from "resources/lib/restApi/RTK";
import { IHelpStepItem } from "../types";

interface ICustomRetDataWithHelpSteps {
  isSuccess: boolean,
  statusCode: number,
  message: string,
  data: IHelpStepItem[]
}

const api = RTK.injectEndpoints({
  endpoints: (build) => ({
    getHelpStepsList: build.query<ICustomRetDataWithHelpSteps, undefined>({
      query: () => ({
        url: "/helpSteps"
      })
    })
  })
});

export const useHelpStepsList = api.useGetHelpStepsListQuery;
