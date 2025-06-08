import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo } from "react";
import NotificationSVG from "resources/assets/icons/notification.svg";
import { MenuPopover } from "shared/MenuPopover/MenuPopover";
import { NotificationsList } from "entities/Notificatons";
import { IconSVG } from "shared/IconSVG/IconSVG";
import { getUserAuthData } from "entities/User";
import { useSelector } from "react-redux";
import cls from "./NotificationButton.module.scss";

interface INotificationButtonProps {
  className?: string;
}

export const NotificationButton = memo(({ className }: INotificationButtonProps) => {
  const authData = useSelector(getUserAuthData);

  if (!authData) { return null; }

  return (
    <MenuPopover
      className={classes(cls.NotificationButton, {}, [className])}
      direction="bottomLeft"
      trigger={<IconSVG Svg={NotificationSVG} />}
    >
      <NotificationsList />
    </MenuPopover>
  );
});
