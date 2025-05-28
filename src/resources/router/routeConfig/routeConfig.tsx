import { AboutPage } from "pages/AboutPage";
import { BooksListPage } from "pages/BooksListPage";
import { Page404 } from "pages/Page404";
import { ProfilePage } from "pages/ProfilePage";
import { RouteProps } from "react-router-dom";
import { BookDetailsPage } from "pages/BookDetailsPage";
import { EditBookPage } from "pages/EditBookPage";
import { DMCAPage } from "pages/DMCA";
import { Help } from "pages/Help";
import { AdminPage } from "pages/AdminPage";
import { EUserRoles } from "entities/User";
import { ForbiddenPage } from "pages/ForbiddenPage";

export type AppRoutesPropsT = RouteProps & {
  authOnly?: boolean;
  roles?: EUserRoles[];
}

export enum AppRoutes {
  BOOKS = "books",
  BOOK_DETAILS = "book_details",
  BOOK_ADD = "book_add",
  BOOK_EDIT = "book_edit",
  DMCA_REPORT = "dmca_report",
  HELP = "help",
  ABOUT = "about",
  PROFILE = "profile",
  ADMIN = "admin",
  FORBIDDEN = "forbidden",
  PAGE_404 = "404"
}

export const RoutePath: Record<AppRoutes, string> = {
  [AppRoutes.BOOKS]: "/", // "/books",
  [AppRoutes.BOOK_DETAILS]: "/books/", // + {id}
  [AppRoutes.BOOK_ADD]: "/books/add",
  [AppRoutes.BOOK_EDIT]: "/books/:id/edit",
  [AppRoutes.HELP]: "/help",
  [AppRoutes.ADMIN]: "/admin",
  [AppRoutes.FORBIDDEN]: "/forbidden",
  [AppRoutes.DMCA_REPORT]: "/DMCA_rep",
  [AppRoutes.ABOUT]: "/about",
  [AppRoutes.PROFILE]: "/profile/", // + {id}
  [AppRoutes.PAGE_404]: "*"
};

export const routeConfig: Record<AppRoutes, AppRoutesPropsT> = {
  [AppRoutes.BOOKS]: {
    path: RoutePath.books,
    //   authOnly: true,
    element: <BooksListPage />
  },
  [AppRoutes.BOOK_DETAILS]: {
    path: `${RoutePath.book_details}:id`,
    //   authOnly: true,
    element: <BookDetailsPage />
  },
  [AppRoutes.BOOK_ADD]: {
    path: `${RoutePath.book_add}`,
    authOnly: true,
    element: <EditBookPage />
  },
  [AppRoutes.BOOK_EDIT]: {
    path: `${RoutePath.book_edit}`,
    authOnly: true,
    element: <EditBookPage />
  },
  [AppRoutes.HELP]: { path: RoutePath.help, element: <Help /> },
  [AppRoutes.ABOUT]: { path: RoutePath.about, element: <AboutPage /> },
  [AppRoutes.DMCA_REPORT]: { path: RoutePath.dmca_report, element: <DMCAPage /> },
  [AppRoutes.PROFILE]: {
    path: `${RoutePath.profile}:id`,
    authOnly: true,
    element: <ProfilePage />
  },
  [AppRoutes.ADMIN]: {
    path: `${RoutePath.admin}`,
    authOnly: true,
    element: <AdminPage />,
    roles: [EUserRoles.ADMIN, EUserRoles.MANAGER]
  },
  [AppRoutes.FORBIDDEN]: { path: RoutePath.forbidden, element: <ForbiddenPage /> },
  [AppRoutes.PAGE_404]: { path: RoutePath["404"], element: <Page404 /> },
};
