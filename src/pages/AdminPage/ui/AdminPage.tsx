import { Counter } from "entities/Counter";
import { useTranslation } from "react-i18next";
import { Page } from "widgets/Page/Page";

const AdminPage = () => {
  const { t } = useTranslation();
  return (
    <Page>
      <h2 className="App-link">{t("админка")}</h2>
    </Page>
  );
};

export default AdminPage;
