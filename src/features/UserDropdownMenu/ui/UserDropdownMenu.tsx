import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo, useCallback, useState } from "react";
import UserProfile from "resources/assets/icons/user-profile.svg";
import { DropdownMenu, IDropdownMenuItem } from "shared/MenuDropdown/DropdownMenu";
import { IconSVG } from "shared/IconSVG/IconSVG";
import { useAppDispatch } from "resources/hooks/useAppDispatch";
import { useTheme } from "resources/store/ThemeProvider";
import { getUserAuthData, isUserAdminSelector, isUserManagerSelector, userActions } from "entities/User";
import { LoginModal } from "features/AuthByUserName";
import { useSelector } from "react-redux";
import { RoutePath } from "resources/router/routeConfig/routeConfig";
import { getNavbarItemsArr } from "widgets/Navbar/model/selectors";
import { CImg } from "shared/CImg";
import { Skeleton } from "shared/Skeleton/Skeleton";
import cls from "./UserDropdownMenu.module.scss";

interface IUserDropdownMenuProps {
  className?: string;
}

export const UserDropdownMenu = memo(({ className }: IUserDropdownMenuProps) => {
  const { t, i18n } = useTranslation();

  const changeLang = async () => {
    i18n.changeLanguage(i18n.language === "ru" ? "en" : "ru");
  };

  const { theme, toggleTheme } = useTheme();

  const [isAuthModalWinOpen, setAuthModalWin] = useState(false);

  const authData = useSelector(getUserAuthData);

  const isUserAdmin = useSelector(isUserAdminSelector);

  const isUserManager = useSelector(isUserManagerSelector);

  const user = useSelector(getUserAuthData);

  const dispatch = useAppDispatch();

  const onAuthModalClose = useCallback(() => {
    setAuthModalWin(false);
  }, []);

  const onAuthModalOpen = useCallback(() => {
    setAuthModalWin(true);
  }, []);

  const onLogout = useCallback(() => {
    dispatch(userActions.logout());
  }, [dispatch]);

  const menuProfileList: IDropdownMenuItem[] = [
    { content: t("тема"), onClick: toggleTheme, id: 1 },
    { content: t("язык"), onClick: changeLang, id: 2 },
  ];

  if (authData) {
    menuProfileList.push(
      { content: t("Профиль"), href: `${RoutePath.profile}${user?.user.id}`, id: 3 }, //  onClick: onProfileCLick },
      { content: t("Выйти"), onClick: onLogout, id: 4 }
    );

    // if (user?.user.roles?.includes(EUserRoles.ADMIN)) {
    if (isUserAdmin || isUserManager) {
      menuProfileList.push(
        { content: t("админка"), href: RoutePath.admin, id: 7 },
        { content: t("добавить"), href: RoutePath.book_add, id: 5 } // , onClick: onAddBook }
      );
    }
  } else {
    menuProfileList.push(
      { content: t("войти"), onClick: onAuthModalOpen, id: 6 }
    );
  }

  const trigger = user?.avatar
    ? (
      <CImg
        style={{ width: 18, height: 18 }}
        src={user?.avatar}
        className={cls.avatar}
        fallback={<Skeleton className={cls.avatar} />}
      />
    )
    : <IconSVG Svg={UserProfile} />;

  return (
    <>
      <DropdownMenu
        className={classes(cls.UserDropdownMenu, {}, [className])}
        direction="bottomLeft"
        items={menuProfileList}
        trigger={trigger}
      />

      {isAuthModalWinOpen && <LoginModal isOpen={isAuthModalWinOpen} onClose={onAuthModalClose} />}
    </>
  );
});
