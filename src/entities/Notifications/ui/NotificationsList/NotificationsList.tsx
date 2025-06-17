import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo } from "react";
import { useGetNotificationsAPI } from "entities/Notifications/api/getNotificationsAPI";
import { VFlex } from "shared/Flex/VFlex";
import { Skeleton } from "shared/Skeleton/Skeleton";
import cls from "./NotificationsList.module.scss";
import { NotificationsItem } from "../NotificationsItem/NotificationsItem";

interface INotificationsListProps {
  className?: string;
}

export const NotificationsList = memo(({ className }: INotificationsListProps) => {
  const { isLoading, data, error } = useGetNotificationsAPI(undefined, {
    pollingInterval: 15000
  });

  if (isLoading) {
    return (
      <VFlex gap="8" max className={classes(cls.NotificationsList, {}, [className])}>
        <Skeleton key="notification-skeleton1" width={350} height={80} />
        <Skeleton key="notification-skeleton2" width={350} height={80} />
        <Skeleton key="notification-skeleton3" width={350} height={80} />
        <Skeleton key="notification-skeleton4" width={350} height={80} />
        <Skeleton key="notification-skeleton5" width={350} height={80} />
      </VFlex>
    );
  }

  return (
    <VFlex gap="8" max className={classes(cls.NotificationsList, {}, [className])}>
      {data?.data?.map((item) => <NotificationsItem key={item.id} item={item} />)}
    </VFlex>
  );
});
