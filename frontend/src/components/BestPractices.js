import * as React from 'react';
import { useEffect, useState } from 'react';
import {
  AppBar,
  Grid,
  MenuItem,
  Select,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  FormControl, InputLabel, IconButton,
  TextField,
  Toolbar,
  Typography,
} from '@mui/material';
import {PracticeDialog} from "./PracticeDialog";
import Add from '@mui/icons-material/Add';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import TextsmsIcon from '@mui/icons-material/Textsms';
import ButtonComponent from "./Button";
import axios from "axios";
import { categories, teams } from "./consts";
import {CommentsDialog} from "./CommentsDialog";

const TestData = [
  {
    id: 1,
    name: 'Практика 1',
    category: 'Категория 1',
    team: 'Команда 1',
    votes: 10,
    author: 'Автор 1',
  },
  {
    id: 2,
    name: 'Практика 2',
    category: 'Категория 2',
    team: 'Команда 2',
    votes: 8,
    author: 'Автор 2',
  },
  {
    id: 3,
    name: 'Практика 3',
    category: 'Категория 1',
    team: 'Команда 3',
    votes: 5,
    author: 'Автор 3',
  },
];

function BestPractices() {
  const [category, setCategory] = React.useState('all');
  const [team, setTeam] = React.useState('all');
  const [search, setSearch] = React.useState('');
  const [practiceDialogOpen, setPracticeDialogOpen] = useState(false);
  const [commentDialogOpen, setCommentDialogOpen] = useState(false);
  const [practices, setPractices] = React.useState([]);

  const handlePracticeDialogOpen = () => {
    setPracticeDialogOpen(true);
  }

  const handleExit = () => {
      return axios.get(document.location + '/logout');
  }

  const handleLike = (id) => {
    axios.post(`/api/practices/${id}/rate`, id);
  }

  const handleSearch = () => {
    const condition = {
      name: search,
      team: team === 'all' ? null : team,
      category: category === 'all' ? null : team
    };
    axios.post('/api/practices/search', condition);
  }

  useEffect(() => {
    // axios.get(``)
    //   .then((response) => setPractices(response.data));
    setPractices(TestData);
  },[]);

  return (
    <div className="bg-background-page ">
      <AppBar position="static" className="bg-page-header">
        <Toolbar>
          <Typography variant="h6" className="text-white" component="div" sx={{ flexGrow: 1 }}>
            Наилучшие практики
          </Typography>
          <ButtonComponent onClick={handleExit}>Выйти</ButtonComponent>
        </Toolbar>
      </AppBar>
      <div>
        <img src={require('../logo.jpg')} alt="logo" height="150" className="ml-16 mt-16" />
      </div>
      {/* Кнопка добавления новой практики */}
      <ButtonComponent
        variant="contained"
        className="ml-16 mt-16"
        startIcon={<Add />}
        onClick={handlePracticeDialogOpen}
      >
        Добавить новую практику
      </ButtonComponent>

      <Grid container className="mt-16 px-8">
        <Grid item xs={12} sm={4} className="px-8">
          <TextField
            label="Поиск"
            fullWidth
            margin="normal"
            value={search}
            className="m-0"
            onChange={(e) => setSearch(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={3} className="px-8">
          <FormControl className="w-full">
            <InputLabel id="category">Категория</InputLabel>
            <Select
              displayEmpty
              value={category}
              onChange={(e) => setCategory(e.target.value)}
              fullWidth
              id="category"
              label="Категория"
              margin="normal"
            >
              <MenuItem key="all" value='all'>Все</MenuItem>
              {categories.map((option) => (
                <MenuItem key={option} value={option}>
                  {option}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={3} className="px-8">
          <FormControl className="w-full">
            <InputLabel id="team">Команда</InputLabel>
            <Select
              displayEmpty
              value={team}
              onChange={(e) => setTeam(e.target.value)}
              fullWidth
              id="team"
              label="Команда"
              margin="normal"
            >
              <MenuItem key="all" value='all'>Все</MenuItem>
              {teams.map((option) => (
                <MenuItem key={option} value={option}>
                  {option}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={2} className="px-8">
          <ButtonComponent
            variant="contained"
            className="w-full h-full"
            onClick={handleSearch}
          >
            Поиск
          </ButtonComponent>
        </Grid>
      </Grid>

          {/* Таблица */}
          <Table className="bg-background-table mt-16">
            <TableHead className="bg-table-header text-white">
              <TableRow >
                <TableCell className="text-white">Наилучшие практики</TableCell>
                <TableCell className="text-white">Категория</TableCell>
                <TableCell className="text-white">Команда</TableCell>
                <TableCell className="text-white">Количество голосов</TableCell>
                <TableCell className="text-white">Автор</TableCell>
                <TableCell className="text-white">Голосовать</TableCell>
                <TableCell className="text-white">Комментарии</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {practices.map((item) => (
                <TableRow key={item.id}>
                  <TableCell>
                    <a href={item.link} target="_blank" rel="noreferrer" className="text-black">
                      {item.name}
                    </a>
                  </TableCell>
                  <TableCell>{item.category}</TableCell>
                  <TableCell>{item.team}</TableCell>
                  <TableCell>{item.votes}</TableCell>
                  <TableCell>{item.author}</TableCell>
                  <TableCell>
                    <IconButton aria-label="upload picture"
                                className="text-button"
                                disabled={false}
                                onClick={() => handleLike(item.id)}>
                      <ThumbUpIcon />
                    </IconButton>
                  </TableCell>
                  <TableCell>
                    <IconButton
                        aria-label="comment button"
                        className="text-button"
                        onClick={() => setCommentDialogOpen(true)}
                    >
                      <TextsmsIcon/>
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
      <PracticeDialog dialogOpen={practiceDialogOpen} setDialogOpen={setPracticeDialogOpen} />
      <CommentsDialog dialogOpen={commentDialogOpen} setDialogOpen={setCommentDialogOpen} />
    </div>
  );
}

export default BestPractices;