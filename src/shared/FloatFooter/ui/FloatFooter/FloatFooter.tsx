import { classes } from "resources/lib/classNames/classes";

import { memo, ReactNode } from "react";

import { INavbarItem } from "widgets/Navbar/model/types";
import { RoutePath } from "resources/router/routeConfig/routeConfig";
import { HFlex } from "shared/Flex/HFlex";
import { useTranslation } from "react-i18next";
import { IFloatFooterItem } from "../../model/types";
import cls from "./FloatFooter.module.scss";
import { NavbarItem } from "../NavbarItem/NavbarItem";

interface IFloatFooterProps {
  className?: string;
  children?: ReactNode;
}

// const itemsArr: IFloatFooterItem[] = [
//   {
//     path: RoutePath.dmca_report,
//     text: "DMCA_Rep"
//   },
//   {
//     path: RoutePath.forbidden,
//     text: "Notifications"
//   },
// ];

// const NavbarItemList = itemsArr.map((item: INavbarItem) => (
//   <NavbarItem
//     key={item.path}
//     item={item}
//   />
// ));

export const FloatFooter = memo(({ className, children }: IFloatFooterProps) => {
  // const { t } = useTranslation();

  // const onContactHandler = () => {
  //   window.location.href = `mailto:${__CONTACT_US_EMAIL__}`;
  // };

  return (
    <footer className={classes(cls.FloatFooter, {}, [className])}>
      <HFlex className={cls.itemWrapper} justify="center">
        {children}
        {/* <div className={cls.warnings}> */}
        {/* {`${t("warnings")} `} */}

        {/* <NavbarItem item={{ path: RoutePath.dmca_report,
            text: "DMCA_Rep" }}
          /> */}

        {/* <div
            className={classes(cls.warnings, {}, [cls.contactUsLink])}
            onClick={onContactHandler}
          >
            {t("DMCA_Rep")}
          </div> */}
        {/* </div> */}

        {/* { NavbarItemList } */}

      </HFlex>
    </footer>
  );
});
