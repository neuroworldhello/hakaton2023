import * as React from 'react';
import { useCallback, useEffect, useState } from 'react';
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
  Typography, Tooltip,
} from '@mui/material';
import { PracticeDialog } from "./PracticeDialog";
import Add from '@mui/icons-material/Add';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import TextsmsIcon from '@mui/icons-material/Textsms';
import ButtonComponent from "./Button";
import axios from "axios";
import { categories, teams } from "./consts";
import { CommentsDialog } from "./CommentsDialog";
import SortIcon from '@mui/icons-material/Sort';

function BestPractices() {
  const [category, setCategory] = React.useState('all');
  const [team, setTeam] = React.useState('all');
  const [search, setSearch] = React.useState('');
  const [sortRating, setSortRating] = React.useState('DESC');
  const [loading, setLoading] = React.useState(true);
  const [practiceDialogOpen, setPracticeDialogOpen] = useState(false);
  const [commentDialogOpen, setCommentDialogOpen] = useState(false);
  const [practices, setPractices] = React.useState([]);
  const [user, setUser] = React.useState('');
  const [practiceId, setPracticeId] = useState();

  const handlePracticeDialogOpen = useCallback(() => {
    setPracticeDialogOpen(true);
  }, []);

  const handleExit = useCallback(() => {
    return axios.get('/logout')
      .then(window.location.href = '/login.html');
  }, []);

  const handleSort = useCallback(() => {
    setSortRating(prev => prev === 'DESC' ? 'ASC' : 'DESC');
  }, []);

  const handleOpenCommentDialog = useCallback((practiceId) => {
    setPracticeId(practiceId);
    setCommentDialogOpen(true);
  }, []);

  const handleSearch = useCallback(() => {
    setLoading(true);
    const condition = {
      name: search,
      team: team === 'all' ? null : team,
      category: category === 'all' ? null : category,
      sortByRatingDirection: sortRating
    };
    axios.post('/api/practices/search', condition)
      .then(resp => setPractices(resp.data))
      .then(() => setLoading(false));
  }, [search, team, category, sortRating]);

  useEffect(() => {
    axios.post('/api/practices/search', {})
      .then(resp => setPractices(resp.data))
      .then(() => setLoading(false));
    axios.get('/username')
      .then(resp => setUser(resp.data));
  }, []);

  const handleLike = useCallback((id) => {
    axios.post(`/api/practices/${id}/rate`, id)
      .then(handleSearch);
  }, [handleSearch]);

  useEffect(() => {
    handleSearch();
  }, [sortRating]);

  const handleKeyDown = useCallback((event) => {
    if (event.key === 'Enter') {
      handleSearch();
    }
  }, [handleSearch]);

  return (
    <div className="bg-background-page ">
      <AppBar position="static" className="bg-page-header">
        <Toolbar>
          <Typography variant="h6" className="text-white" component="div" sx={{ flexGrow: 1 }}>
            Лучшие практики
          </Typography>
          <Typography variant="h6" className="text-white mr-16" component="div">
            Пользователь: {user}
          </Typography>
          <ButtonComponent onClick={handleExit}>Выйти</ButtonComponent>
        </Toolbar>
      </AppBar>
      <div>
        <img src={require('../logo.jpg')} alt="logo" height="120" className="ml-16 mt-16" />
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
            onKeyDown={handleKeyDown}
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
            disabled={loading}
          >
            Поиск
          </ButtonComponent>
        </Grid>
      </Grid>

      {/* Таблица */}
      <Table className="bg-background-table mt-16">
        <TableHead className="bg-table-header text-white">
          <TableRow>
            <TableCell className="text-white" width='50%'>Наилучшие практики</TableCell>
            <TableCell className="text-white " width='9%'>Категория</TableCell>
            <TableCell className="text-white " width='9%'>Команда</TableCell>
            <TableCell width='9%'>
              <div className="text-white flex items-center">
                Количество голосов
                <IconButton onClick={handleSort} disabled={loading}>
                  <SortIcon className={` text-button-text ${sortRating === 'ASC' && "revert-180"}`} />
                </IconButton>
              </div>
            </TableCell>
            <TableCell className="text-white " width='9%'>Автор</TableCell>
            <TableCell className="text-white " width='7%'>Голосовать</TableCell>
            <TableCell className="text-white " width='7%'>Комментарии</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {practices.map((item) => (
            <TableRow key={item.id}>
              <TableCell>
                <a href={item.documentLink} target="_blank" rel="noreferrer" className="text-black">
                  {item.name}
                </a>
              </TableCell>
              <TableCell>{item.category}</TableCell>
              <TableCell>{item.team}</TableCell>
              <TableCell>{item.rating}</TableCell>
              <TableCell>{item.author}</TableCell>
              <TableCell>
                <Tooltip title={item.isAlreadyVoted && 'Вы уже проголосовали за эту практику'}>
                  <span>
                    <IconButton aria-label="upload picture"
                                className="text-button disabled:text-body-font"
                                disabled={item.isAlreadyVoted}
                                onClick={() => handleLike(item.id)}>
                      <ThumbUpIcon />
                    </IconButton>
                  </span>
                </Tooltip>
              </TableCell>
              <TableCell>
                <IconButton
                  aria-label="comment button"
                  className="text-button"
                  onClick={() => handleOpenCommentDialog(item.id)}
                >
                  <TextsmsIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <PracticeDialog dialogOpen={practiceDialogOpen} setDialogOpen={setPracticeDialogOpen}
                      handleSearch={handleSearch} />
      <CommentsDialog dialogOpen={commentDialogOpen} setDialogOpen={setCommentDialogOpen} practiceId={practiceId}/>
    </div>
  );
}

export default BestPractices;