import { useTranslation } from "react-i18next";
import { Text } from "shared/Text";
// import src from "resources/assets/images/img2.png";

interface HeaderProps {
  className?: string;
}

export const Header = ({ className }: HeaderProps) => {
  const { t } = useTranslation("profile");
  // https://priroda.club/uploads/posts/2023-08/1691159252_priroda-club-p-lesnoi-veter-vkontakte-57.jpg
  return (
    <Text title={t("Карточка профиля")} />
  );
};
