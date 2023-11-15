import { localAxios } from "@/utils/http-common";

const local = localAxios(); // axios instance

const url = "/board"

function listArticle(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

function registArticle(form, success, fail) {
  console.log("boardjs article", form);
  local.post(`${url}`, form,  {
    headers: {
    'Content-Type': 'multipart/form-data'
    }
    }).then(success).catch(fail);
}

export {
  listArticle,
  registArticle,
};
