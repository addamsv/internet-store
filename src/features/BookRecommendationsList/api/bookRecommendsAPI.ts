import { IBook } from "entities/Book";
import { RTK } from "resources/lib/restApi/RTK";

interface ICustomReturnDataWithBooks {
  isSuccess: boolean,
  statusCode: number,
  message: string,
  data: IBook[]
}

const api = RTK.injectEndpoints({
  endpoints: (build) => ({
    getBooksRecommendationsList: build.query<ICustomReturnDataWithBooks, number>({
      query: (limit) => ({
        url: "/books",
        params: {
          _limit: limit,
        }
      })
    }),
    createBooksRecommendation: build.mutation({
      query: (limit) => ({
        url: "/books",
        params: {
          _limit: limit,
        },
        method: "POST",
        body: {
          f: ""
        }
      })
    })
  })
});

export const useBooksRecommendationsList = api.useGetBooksRecommendationsListQuery;
export const useCreateBooksRecommendation = api.useCreateBooksRecommendationMutation;
