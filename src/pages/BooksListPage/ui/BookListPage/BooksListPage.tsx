/* eslint-disable max-len */
import { classes } from "resources/lib/classNames/classes";
import { memo, useCallback, useEffect } from "react";
import { EBookOfHashTagType } from "entities/Book/model/consts";
import { AsyncModule, ReducerListT } from "shared/AsyncModule/AsyncModule";
import { useAppDispatch } from "resources/hooks/useAppDispatch";
import { useSelector } from "react-redux";
import { Page } from "widgets/Page/Page";
import { useSearchParams } from "react-router-dom";
import { FloatFooter } from "shared/FloatFooter";
import { useDebounce } from "resources/hooks/useDebounce";
import { Footer } from "widgets/Footer";
import { bookListPageActions, bookListPageReducer } from "../../model/slices";
import cls from "./BooksListPage.module.scss";
import { fetchBookList } from "../../model/services/fetchBookList";
import { fetchNextBookList } from "../../model/services/fetchNextBookList";
import { getBooksListPageIsStateInit } from "../../model/selectors";
import { initBookListPage } from "../../model/services/initBookListPage";
import { BookListFilters } from "../Filter/BookListFilters";
import { BooksList } from "../BooksList/BooksList";

interface IBooksListPageProps {
  className?: string;
}

const reducers: ReducerListT = {
  bookListPage: bookListPageReducer
};

const BooksListPage = ({ className }: IBooksListPageProps) => {
  // const { t } = useTranslation("book");

  const dispatch = useAppDispatch();

  // SELECTORS
  const isStateInit = useSelector(getBooksListPageIsStateInit);
  const [searchParams, setSearchParams] = useSearchParams();

  const onNextChunk = useCallback(() => {
    dispatch(fetchNextBookList());
  }, [dispatch]);

  /** FOR INIT STATE ONLY */
  useEffect(() => {
    if (__PROJECT_TYPE__ !== "storybook" && __PROJECT_TYPE__ !== "jest") {
      dispatch(initBookListPage(searchParams));
    }
  }, [dispatch, isStateInit, searchParams]);

  const fetch = useCallback(() => {
    dispatch(fetchBookList({ shouldReplace: true }));
  }, [dispatch]);

  const debouncedFetch = useDebounce(fetch, 500);

  const onGenreChange = useCallback((genre: EBookOfHashTagType) => {
    dispatch(bookListPageActions.setHashTag(genre));
    dispatch(bookListPageActions.setPage(1));
    fetch();
  }, [dispatch, fetch]);

  const onSearchQueryChange = useCallback((query: string) => {
    dispatch(bookListPageActions.setSearch(query));
    dispatch(bookListPageActions.setPage(1));
    debouncedFetch();
  }, [dispatch, debouncedFetch]);

  return (
    <AsyncModule reducers={reducers} isRemoveAfterUnmount={false}>
      <Page
        onNextChunk={onNextChunk}
        className={classes(cls.BooksListPage, {}, [className])}
      >
        <BookListFilters key="BookListFilters" onGenreChange={onGenreChange} onSearchQueryChange={onSearchQueryChange} />

        <BooksList onGenreChange={onGenreChange} onSearchQueryChange={onSearchQueryChange} />

        <FloatFooter key="FloatFooter">
          <Footer />
        </FloatFooter>

      </Page>
    </AsyncModule>
  );
};

export default memo(BooksListPage);
