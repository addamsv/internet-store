import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo } from "react";
import { Card } from "shared/Card/Card";
import { ETextAlign, Text } from "shared/Text";
import { AppLink } from "shared/AppLink/AppLink";
import cls from "./NotificationsItem.module.scss";
import { INotificationsItem } from "../../types";

interface INotificationsItemProps {
  className?: string;
  item: INotificationsItem
}

export const NotificationsItem = memo(({ className, item }: INotificationsItemProps) => {
  const { t } = useTranslation();

  return (
    <Card className={classes(cls.NotificationsItem, {}, [className])}>
      <Text title={item.title} text={item.description} textAlign={ETextAlign.LEFT} />
      {item.link && <AppLink to={item.link}>{t("подробнее")}</AppLink>}
    </Card>
  );
});
