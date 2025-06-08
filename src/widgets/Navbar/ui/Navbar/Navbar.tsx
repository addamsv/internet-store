import { classes } from "resources/lib/classNames/classes";

import { memo } from "react";
import { useSelector } from "react-redux";

import { INavbarItem } from "widgets/Navbar/model/types";

import { HFlex } from "shared/Flex/HFlex";
import { getUserAuthData } from "entities/User";
import BasketSVG from "resources/assets/icons/basket.svg";
import { RoutePath } from "resources/router/routeConfig/routeConfig";
import { AppLink } from "shared/AppLink/AppLink";
import { IconSVG } from "shared/IconSVG/IconSVG";
import { NotificationButton } from "features/NotificationButton";
import { UserDropdownMenu } from "features/UserDropdownMenu";
import { getNavbarItemsArr } from "../../model/selectors";
import cls from "./Navbar.module.scss";
import { NavbarItem } from "../NavbarItem/NavbarItem";

interface INavbarProps {
  className?: string;
}

export const Navbar = memo(({ className }: INavbarProps) => {
  const navbarArr = useSelector(getNavbarItemsArr);

  const NavbarItemList = navbarArr.map((item: INavbarItem) => (
    <NavbarItem
      key={item.path}
      item={item}
    />
  ));

  return (
    <header className={classes(cls.Navbar, {}, [className])}>
      <HFlex Tag="nav" role="navigation" className={cls.links}>
        { NavbarItemList }
      </HFlex>

      <HFlex justify="end" gap="16" align="start" className={cls.rightMenuWrapper}>

        <AppLink target="blank" to={RoutePath.help} style={{ paddingTop: 3, height: 19 }}>
          <IconSVG Svg={BasketSVG} />
        </AppLink>

        <NotificationButton />

        <UserDropdownMenu />

      </HFlex>
    </header>
  );
});
